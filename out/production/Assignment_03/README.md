COMPX202-18B / COMPX242-18B Assignment 3
========================================

Due on **Friday 3rd August at 11:30 pm**.

Java Generics and Testing
-------------------------

**Purpose**: The main purpose of this assignment is to experiment with
the object-oriented features of Java and with generics. The secondary
purpose is to give you some experience with the important modern
software engineering practice of unit testing.

**Tests**: You are required to write JUnit tests for all your code
demonstrating that it follows the assignment specifications.

**Git**: Remember to use Git throughout. Commit regularly with
descriptive messages.

Introduction
------------

A queue is a collection that supports adding elements to one end and
removing elements from the other end (i.e. a first-in-first-out data
structure). Queues have a wide range of applications, for example
passing messages.

For this assignment you will implemented a bounded (fixed-capacity)
queue and use it in the setting of a "game". The game has some state
(player's location and score) and there are actions which change the
game state. The actions are sent to the game through a queue.

Using a fixed-capacity queue limits memory usage. When the queue is
full items will start getting dropped, which allows the game to
recover if the actions can't be processed quickly enough. There are
two strategies for dropping items: drop the item which has been in the
queue the longest, or drop the most recent item which is currently
being added.

(In a more realistic setting the queue might be concurrent, but that
is beyond the scope of this assignment.)

Instructions
------------

### Part A: Setup

1. Fork this repository using the button at the top of the project
    page.

2. **Set the visibility of your project to Private** (Settings >
    expand Permissions > Project visibility: Private; Save changes).

3. Clone your new repository to your computer using Git or IntelliJ
    IDEA.

4. Try running the example test in GameTest.java. It should pass.
    - IntelliJ IDEA: Right click the file and choose _Run 'GameTest'_ (JUnit should already be configured)
    - Terminal (Linux/macOS):
       1. Run `export CLASSPATH=".:lib/*"` (do this once in each terminal you open)
       2. Compile normally (`javac GameTest.java`)
       3. Run `java org.junit.runner.JUnitCore GameTest`
    - Windows Command Prompt:
       1. Run `set CLASSPATH=.;lib/*` (you'll need to do this once in each Command Prompt window you open)
       2. Compile normally (`javac GameTest.java`)
       3. Run `java org.junit.runner.JUnitCore GameTest`

5. Try running the tests in BoundedQueueTest.java. They should fail
    because BoundedQueue is not implemented.

### Part B: Bounded Queue

There are a few different ways to implement the queue. You may choose
any way that you like, but you **must not** use any of the Java
collection classes. You may use arrays; if you do, the provided method
_makeArray_ will be useful (Java does not support generic array
creation using `new T[size]` where T is a type parameter).

BoundedQueue.java contains a skeleton for you to fill out. Replace the
lines `throw new RuntimeException("Not yet implemented");` with your
own code (but don't change the method signatures). You may add members
as required.

Write the tests in any order you like (before or after you write the
code). I suggest using String as the element type for the tests, but
be aware that in Java strings should be compared using
`str1.equals(str2)` rather than `str1 == str2`.

You should not use casts, e.g. `(String) someVariable`, in any of the
code for this assignment. The method _makeArray_ is exempt from this
requirement.

### Part C: Reporting Statistics

Add a static method to BoundedQueue called _reportStats_ which prints
to standard out (i.e. _System.out.println_) the following statistics,
measured from when the program started running:

 - the number of BoundedQueue instances that have been created
 - the total number of items dropped from BoundedQueue instances

You are not required to write a unit test for this method. Instead,
create a method in BoundedQueueTest which calls _reportStats_ and
annotate it with `@AfterClass` instead of `@Test` so that it will run
after all the test cases. Manually check that the statistics look
reasonable.

### Part D: Game

The provided Game class represents the state of the game and the
player. Action classes modify the state of the game. For example, the
ScoreAction class (provided in Game.java) changes the player's score.

1. Write a MoveAction class that inherits from Action and updates the
    player's location. Example:

        Game game = new Game();
        // ...
        // suppose the player's location is (x=75, y=120)
        Action move = new MoveAction(5, -3);
        move.actOn(game);
        // the player's location should now be (x=80, y=117)

2. Add a method _process_ to Game that takes a `BoundedQueue<Action>`
    and runs all the actions. Then make it more general so it also
    accepts e.g. `BoundedQueue<MoveAction>` (you may find bounded
    wildcards useful).

3. Add a method _generateMovements_ to Game that takes a
    `BoundedQueue<MoveAction>` and adds two MoveActions to it. Then
    make it more general so it also accepts e.g.
    `BoundedQueue<Action>` (you may find bounded wildcards useful).

4. For simplicity, write a single test in GameTest.java that tests
     _generateMovements_ and _process_ together (it can also cover
     _MoveAction_).

### Part E: Alternative Implementations

Describe in two or three paragraphs another way to implement the
bounded queue which is different from the one you used.

> Write your answer here

Submitting
----------

Push your changes to your GitLab repository. Ensure that you can see
your changes on GitLab. Upload a zipped copy of the latest version of
your repository to Moodle.

Grading
-------

| Weighting | Allocated to |
|:----------:|------|
| 10% | Correct repository usage and settings |
| 20% | BoundedQueue implementation |
| 20% | BoundedQueue tests |
| 10% | Part C: _reportStats_ |
| 30% | Part D: MoveAction, _process_, _generateMovements_, test |
| 10% | Part E: alternative implementations |
