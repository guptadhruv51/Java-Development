package com.example.minorproject1.service;

import com.example.minorproject1.dtos.GetStudentDetailsResponse;
import com.example.minorproject1.models.*;
import com.example.minorproject1.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService
{
    @Value("${transactions.books.number}")
    private Integer numberofBooks;
    @Value("${books.return.duration}")
    private Integer noOfDays;
    @Value("${fine.per.day}")
    private Integer finePerDay;
    @Autowired
    StudentService studentService;
    @Autowired
    BookService bookService;
    @Autowired
    TransactionRepository transactionRepository;

    public List<Transactions> getTransactionList(Integer bookID) throws Exception
    {
        return this.bookService.getTransactions(bookID);
    }
    public String initiateTransaction(Integer studentId, Integer bookId,
                                      TransactionType transactionType) throws Exception {
        switch(transactionType)
        {
            case Issue:
                return initiateIssue(studentId,bookId);
            case Return:
                return initiateReturn(studentId,bookId);
            default:
                throw new Exception("Invalid Transaction Type");
        }
    }
    /**
     * Logic:
     * ---------------------------Validations need to be done at service layer --------------------------------------
     *  Validate a student and book, throw 400/404 if any of these details are invalid.
     *  Validate whether the book is available or not
     *  Validate student's limit of issuance for number of books
     *  ----------------------------------------------------------------------------
     *  Create transaction entry in the transaction table with status a Pending
     *
     *  Make the book unavailable so that no one can issue the book concurrently /assign it to the student (book.student=?)
     *  Update the transaction entry with the status as success
     *  If any issue in the last two-steps update transaction entry with failed
     */
    /**
     * Optimistic Locking:
     *  one extra will be maintained that contains a version number.
     *
     * Pessimistic Locking: insert/update/delete: Lock taken on the table.
     * However, the speed will be compromised.
     */
    private String initiateIssue(Integer StudentId, Integer bookId) throws Exception
    {
        //------------------------Data Retrieval-----------------------
    Student student = this.studentService.getStudentDetailsResponse(StudentId, true).getStudent();

    if(student==null)
    {
        throw new Exception("Student not present");
    }
    Book book=this.bookService.getById(bookId);
    if(book==null || book.getStudent()!=null)
    {
        throw new Exception("Book cannot be issued");
    }
    List<Book> issuedBooks=student.getBookList();
    if(issuedBooks!=null && issuedBooks.size()>=numberofBooks)
    {
        throw new Exception("Student has reached maximum limit for the books allowed");
    }
    //--------------------Issuance Logic --------------------------------------
    Transactions transaction= Transactions.builder().
        student(student) //adding a student key for transaction
            .externalId(UUID.randomUUID().toString())
            .book(book)
            .transactionType(TransactionType.Issue)
        .transactionStatus(TransactionStatus.Pending).build();

   transaction= transactionRepository.save(transaction);
   try
   {
       book.setStudent(student); // joining student and book
       book=this.bookService.createOrUpdate(book);
       transaction.setTransactionStatus(TransactionStatus.Success);
       this.transactionRepository.save(transaction);
   }
   catch(Exception e)
   {
       transaction.setTransactionStatus(TransactionStatus.Failed);
       this.transactionRepository.save(transaction);
       if(book.getStudent()!=null)
       {
           book.setStudent(null); //removing the association from the student table for this record
           this.bookService.createOrUpdate(book);
       }
   }
   return transaction.getExternalId();
    }
    /**
     * Logic:
     * ---------------------------Validations need to be done at service layer --------------------------------------
     *  Validate a student and book, throw 400/404 if any of these details are invalid.
     *  Validate whether the book is assigned or not and if assigned it should be to that student.
     *  ----------------------------------------------------------------------------
     *  Create transaction entry in the transaction table with status a Pending.
     *  Make the book available so that anyone can issue the book concurrently /assign it to the student (book.student=?)
     *  Update the transaction entry with the status as success
     *  If any issue in the last two-steps update transaction entry with failed
     */
private String initiateReturn(Integer StudentId, Integer bookId) throws Exception {
        //------------------------Data Retrieval-----------------------
        Student student = this.studentService.getStudentDetailsResponse(StudentId, true).getStudent();

    if(student==null)
    {
        throw new Exception("Student not present");
    }
    Book book=this.bookService.getById(bookId);
    if(book.getStudent()==null || book.getStudent().getId()!=StudentId)
    {
        throw new Exception("Book cannot be returned");
    }
    Integer fine=this.calculatefine(book,student);
    Transactions transaction= Transactions.builder().
            student(student) //adding a student key for transaction
            .externalId(UUID.randomUUID().toString())
            .book(book)
            .fine(fine)
            .transactionType(TransactionType.Return)
            .transactionStatus(TransactionStatus.Pending).build();

    transaction= transactionRepository.save(transaction);
    try
    {
        book.setStudent(null); // unassigned the book
        book=this.bookService.createOrUpdate(book);
        transaction.setTransactionStatus(TransactionStatus.Success);
        this.transactionRepository.save(transaction);
    }
    catch(Exception e)
    {
        transaction.setTransactionStatus(TransactionStatus.Failed);
        this.transactionRepository.save(transaction);
        if(book.getStudent()==null)
        {
            book.setStudent(student); //
            this.bookService.createOrUpdate(book);
        }
    }
    return transaction.getExternalId();

    }

    /**
     *
     * Get issuance transaction
     * Calculate time taken from the transaction updatedOn to current time
     *
     */
    public Integer calculatefine(Book book, Student student)
    {
    Transactions txn=this.transactionRepository.
            findTopByStudentAndBookAndTransactionTypeAndTransactionStatusOrderByIdDesc
                    (student,book,TransactionType.Issue,TransactionStatus.Success);
    Long issuedTimeinMillis=txn.getUpdatedOn().getTime();

    Long timePassedInMillis=System.currentTimeMillis()-issuedTimeinMillis;
        Long daysPassed=TimeUnit.DAYS.convert(timePassedInMillis,TimeUnit.MILLISECONDS);
        if(daysPassed>this.noOfDays)
            return Math.toIntExact((daysPassed - noOfDays) * finePerDay);
    return 0;
    }
}
