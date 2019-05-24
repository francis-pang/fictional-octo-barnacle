package crackingthecodinginterview.stacksandqueues;

import java.util.Stack;

/**
 * Stack of Plates: Imagine a (literal) stack of plates. If the stack gets too high, it might topple. Therefore, in real
 * life, we would likely start a new stack when the previous stack exceeds some threshold. Implement a data structure
 * SetOfStacks that mimics this. SetOfStacks should be composed of several stacks and should create a new stack once the
 * previous one exceeds capacity. SetOfStacks. push () and SetOfStacks. pop () should behave identically to a single
 * stack (that is, pop ( ) should return the same values as it would if there were just a single stack).
 * FOLLOW UP
 * Implement a function popAt (int index) which performs a pop operation on a specific sub-stack.
 */
public class StackOfPlates<E> {
    final int STACK_CAPACITY = 10;
    private StackNode<E> root;
    private StackNode<E> lastStack;
    private int numberOfStackNodes;

    public StackOfPlates() {
        root = new StackNode();
        lastStack = root;
        numberOfStackNodes = 1;
    }

    public void push (E element) {
        if (lastStack.size == STACK_CAPACITY) { // Create new Stack
            StackNode newStackNode = new StackNode();
            lastStack.next = newStackNode;
            newStackNode.next = newStackNode;
            newStackNode.previous = lastStack;
            lastStack = newStackNode;
            numberOfStackNodes++;
        }
        lastStack.push(element);
    }

    public E pop() {
        E poppedElement = lastStack.pop();
        if (lastStack.size == 0) {
            lastStack = lastStack.previous;
            lastStack.next = null;
            numberOfStackNodes--;
        }
        return poppedElement;
    }

    public E popAt(int index) throws IllegalArgumentException {
        StackNode<E> selectedStackNode = root;
        if (numberOfStackNodes < index) {
            throw new IllegalArgumentException("Index out of bound");
        }
        for (int i = 1; i < index; i++) {
            selectedStackNode = selectedStackNode.next;
        }
        E poppedElement = selectedStackNode.pop();
        if (selectedStackNode.size == 0) {
            if (index == 1) {
                root = selectedStackNode.next;
                root.previous = null;
            } else {
                selectedStackNode.previous.next = selectedStackNode.next;
                selectedStackNode.next.previous = selectedStackNode.previous;
            }
        }
        return poppedElement;
    }

    class StackNode<E> extends Stack<E> {
        StackNode previous;
        StackNode next;
        int size = 0;

        @Override
        public E push(E item) {
            super.push(item);
            size++;
            return item;
        }

        @Override
        public synchronized E pop() {
            size--;
            return super.pop();
        }

    }
}
