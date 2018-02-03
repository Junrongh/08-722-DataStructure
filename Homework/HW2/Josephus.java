import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayDeque;
/**
 * 08-722 Data Structures for Application Programmers.
 * Homework Assignment 2
 * Solve Josephus problem with different data structures
 * and different algorithms and compare running times
 *
 * @author Junrong Huang
 */
public class Josephus {

    /**
     * Uses ArrayDeque class as Queue/Deque to find the survivor's position.
     * @param size Number of people in the circle that is bigger than 0
     * @param rotation Elimination order in the circle. The value has to be greater than 0
     * @return The position value of the survivor
     */
    public int playWithAD(int size, int rotation) {
        // TODO your implementation here
        if (size <= 1 || rotation <= 1) {
            throw new RuntimeException("Size & Rotation have to be integers grater than 2");
        }
        Queue<Integer> queue = new ArrayDeque<Integer>();
        for (int i = 0; i < size; i++) {
            queue.offer(i + 1);
        }
        while (queue.size() > 1) {
            int steps = (rotation - 1) % queue.size();
            for (int j = 0; j < rotation - 1; j++) {
                int temp = queue.poll();
                queue.offer(temp);
            }
            queue.poll();
        }
        return queue.poll();
    }

    /**
     * Uses LinkedList class as Queue/Deque to find the survivor's position.
     * @param size Number of people in the circle that is bigger than 0
     * @param rotation Elimination order in the circle. The value has to be greater than 0
     * @return The position value of the survivor
     */
    public int playWithLL(int size, int rotation) {
        // TODO your implementation here
        if (size <= 1 || rotation <= 1) {
            throw new RuntimeException("Size & Rotation have to be integers grater than 2");
        }
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < size; i++) {
            queue.offer(i + 1);
        }
        while (queue.size() > 1) {
            int steps = (rotation - 1) % queue.size();
            for (int j = 0; j < rotation - 1; j++) {
                int temp = queue.poll();
                queue.offer(temp);
            }
            queue.poll();
        }
        return queue.poll();
    }

    /**
     * Uses LinkedList class to find the survivor's position.
     * However, do NOT use the LinkedList as Queue/Deque
     * Instead, use the LinkedList as "List"
     * That means, it uses index value to find and remove a person to be executed in the circle
     *
     * Note: Think carefully about this method!!
     * When in doubt, please visit one of the office hours!!
     *
     * @param size Number of people in the circle that is bigger than 0
     * @param rotation Elimination order in the circle. The value has to be greater than 0
     * @return The position value of the survivor
     */
    public int playWithLLAt(int size, int rotation) {
        // TODO your implementation here
        if (size <= 1 || rotation <= 1) {
            throw new RuntimeException("Size & Rotation have to be integers grater than 2");
        }
        LinkedList<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < size; i++) {
            queue.offer(i + 1);
        }
        int steps = 0;
        while (queue.size() > 1) {
            steps = (steps + rotation - 1) % queue.size();
            queue.remove(steps);
            System.out.println(queue.size());
        }
        return queue.poll();
    }
}
