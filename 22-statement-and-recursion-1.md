# [Homework 22: Statement and Recursion 1][hw22]

- **Name**: Rithvik Allamaneni <!-- TODO: fill with first and last name (e.g., Brutus Buckeye) -->
- **Dot Number**: allamaneni.1 <!-- TODO: fill with OSU dot number (e.g., buckeye.17) -->
- **Due Date**: 3/04/25 @ 1:50 PM EST <!-- TODO: fill out with due date and time (e.g., 10/17 @ 3:10 PM EST) -->

## Preparation

Previous students would have wanted you to know the following
before you get started (based on 1 review):

- Estimated time to complete the assignment: 120 minutes
- Most common emotion before starting the assignment: ??
- Most common emotion while completing the assignment: ??
- Most common emotion after completing the assignment: ??

If the information above is incomplete, you can help by [providing
your own feedback][feedback-form] after completing this assignment.

## Problems

**This homework is necessary preparation for the lab.** Make sure you
type your answers in files you bring to the lab so that you will not
have to waste time entering your code during the lab.

### Problem 1

> Given the following BL statements, draw the corresponding abstract
> syntax trees as defined by the mathematical model of StatementKernel.
> See Slides 5-12 in Abstract Syntax Trees for some examples.
>
> **Note**: When drawing trees in markdown, it's okay not to adhere to the
> exact syntax you see in the slides. Our solutions make use of raw text
> trees, which you may recall seeing in [the heapsort homework][heapsort].
> You're also welcome to draw the trees and embed them as images in markdown,
> or you can even learn how to use diagram rendering tools like mermaid.

#### Problem 1A

```generic
IF next-is-empty THEN
    move
ELSE
    IF next-is-wall THEN
        turnright
        turnright
        move
    END IF
END IF
```
[text](image2.pdf)

#### Problem 1B

```generic
WHILE true DO
    turnright
    IF next-is-enemy THEN
        TurnAround
    ELSE
        skip
    END IF
    turnleft
END WHILE
```
[text](image2.pdf)

#### Problem 1C

```generic
WHILE next-is-enemy DO
    infect
    TurnAround
    move
    turnright
END WHILE
```
[text](image1.pdf)

#### Problem 1D

```generic
IF next-is-friend THEN
    turnright
    turnright
    WHILE true DO
        infect
    END WHILE
END IF
```
[text](image1.pdf)

#### Problem 1E

```generic
IF next-is-not-empty THEN
    turnleft
    turnleft
ELSE
    WHILE next-is-empty DO
        move
    END WHILE
    IF next-is-enemy THEN
        infect
    END IF
    skip
END IF
```
[text](image1.pdf)

### Problem 2

> Using recursion, complete the body of the following static method.
> Note the use of a Java switch statement. See the Statement slides
> (44-49) for the syntax, purpose, and behavior of this construct.

```java
/**
 * Reports the number of calls to primitive instructions (move, turnleft,
 * turnright, infect, skip) in a given {@code Statement}.
 *
 * @param s
 *            the {@code Statement}
 * @return the number of calls to primitive instructions in {@code s}
 * @ensures <pre>
 * countOfPrimitiveCalls =
 *  [number of calls to primitive instructions in s]
 * </pre>
 */
public static int countOfPrimitiveCalls(Statement s) {
    int count = 0;
    switch (s.kind()) {
        case BLOCK: {
            /*
             * Add up the number of calls to primitive instructions
             * in each nested statement in the BLOCK.
             */
 
            for (int i = 0; i < s.lengthOfBlock(); i++) {
                Statement removed = s.removeFromBlock(i);
                count += countOfPrimitiveCalls(removed);
                s.addToBlock(i, removed);
            }
 
            break;
        }
        case IF: {
            /*
             * Find the number of calls to primitive instructions in
             * the body of the IF.
             */
 
            Statement block = s.newInstance();
            StatementKernel.Condition condition = s.disassembleIf(block);
            count += countOfPrimitiveCalls(block);
            s.assembleIf(condition, block);
 
            break;
        }
        case IF_ELSE: {
            /*
             * Add up the number of calls to primitive instructions in
             * the "then" and "else" bodies of the IF_ELSE.
             */
 
            Statement block1 = s.newInstance();
            Statement block2 = s.newInstance();
            StatementKernel.Condition condition = s.disassembleIfEls(block1,block2);
            count += countOfPrimitiveCalls(block1);
            count += countOfPrimitiveCalls(block2);
            s.assembleIfElse(condition, block1, block2);
            break;
        }
        case WHILE: {
            /*
             * Find the number of calls to primitive instructions in
             * the body of the WHILE.
             */
 
            Statement block = s.newInstance();
            StatementKernel.Condition condition = s.disassembleWhile(block);
            count += countOfPrimitiveCalls(block);
            s.assembleWhile(condition, block);
 
            break;
        }
        case CALL: {
            /*
             * This is a leaf: the count can only be 1 or 0. Determine
             * whether this is a call to a primitive instruction or not.
             */
 
            String call = s.disassembleCall();
            if (call.equals("turnleft") || call.equals("turnright")|| call.equals("skip") || call.equals("move")|| call.equals("infect")) {
                count += 1;
            }
            s.assembleCall(call);
            break;
        }
        default: {
            // this will never happen...can you explain why?
            break;
        }
    }
    return count;
}
```

## Submission

If you have completed the assignment using this template, we recommend
that you convert it to a PDF before submission. If you're not sure
how, check out this [Markdown to PDF guide][markdown-to-pdf-guide].

[hw22]: https://cse22x1.engineering.osu.edu/2231/web-sw2/assignments/homeworks/statement1.html
[feedback-form]: https://forms.gle/qJ1gEM5N1r6X7Poy5
[markdown-to-pdf-guide]: https://therenegadecoder.com/blog/how-to-convert-markdown-to-a-pdf-3-quick-solutions/
[heapsort]: https://cse22x1.engineering.osu.edu/2231/web-sw2/assignments/homeworks/heapsort.html
