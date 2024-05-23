package org.example;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("Hello world!");
        /**
         * mvn repository: to check maven dependencies
         * Build tools:
         * Ant: Basic built tool which is used when we build normal projects
         * Maven: One of the best build tools for backend development
         * Gradle: Best for android developer/frontend
         * <p>
         * Frameworks: Dropwizard, Play, Spring, Spring boot
         * ***********Maven*********
         * GroupId: Entity who has developed a project or a Java Archive
         * ArtifactId: Refers to a project or jar name which we are using
         * Version: Refers to the current version of the project
         * <p>
         * Archetype:
         * scope: when to resolve the dependency (runtime, testing, compile time,import)
         * Maven: pom.xml is the driver of the project management part of it (contains dependencies)
         * gradle: build.gradle format is different content is same
         * Python: requirements.txt pip install -r requirement.txt
         * <p>
         * Node.js: package.json {}
         * <p>
         * Ruby-on-rails: gemfile {collections of gem}
         * <p>
         * Repositories (collection of JAR files) in Maven:
         * Local: Data is stored in your computer, accessible only to the user but not on cloud It is stored in root folder/.m2. Only one single /.m2 folder for all the projects
         * Whenever some dependency is downloaded from the central repository, it will be put in local repository. Dependency also needs the POM file to
         * resolve the dependency.
         * ************ How does Maven resolve the dependencies?
         *              Check register notes
         * Central: Data can be put on the cloud and anybody can use it
         * Remote: Data can be put on cloud but is not open source, private access
         * Lifecycle/ Phases of maven
         * All the lifecycles in maven are executed sequentially  Clean: is one  phase that needs mentioning
         * Clean: cleans up the byte code and classes and all other classes created during run+compile time
         * Validate: Validates whether pom.xml is there or not (temporary phase not very important)
         * Compile: Compiles the code and looks for any compilation error, builds the target (creates the byte code). looks for syntax error or any errors in Main directory.
         * Test: This phase runs all the test cases/ compiles all the test classes (test directory: for testing)
         * Package: Creates a JAR file nd puts it in the target folder(Advantage: Can share JAR file and can be used to deploy any application on the server (copy this jar and run it on the server)
         *                                          Can be used a dependency in some other project ()
         *                                          Can be used for versioning) and put fit in target folder
         * Verify: Verifies if target contains the JAR file of the current project
         * Install: Copies the JAR and pom to local m2 repository so that it can be usd in some other project locally(Installs the required local, remote and central dependencies)
         * Site: Looks for the remote repo and tries to create a connection with it
         * Deploy: used for deploying or copying the artifact (jar and pom) from local m2 to remote repository
         * *********************************Plugins ****************
         * Whenever we execute the lifecycle, plugins contains those steps (contains the code/steps to take)
         * Surefire: test cases
         * Jar: is plugin jar:help is the goal (contains the methods and everything to carry out those steps)
         * There will be different plugins for different projects such as Spring boot/ Spring containing different tasks and code
         */
    }
}