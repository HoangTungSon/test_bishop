package com.company;

import com.company.exercise2.Node;

import java.util.*;

public class Main {

  // Below arrays details all 4 possible movements
  // for a knight
  private static int[] row = {1, 1, -1, -1};
  private static int[] col = {-1, 1, 1, -1};

  // Check if (x, y) is valid chess board coordinates
  // Note that a bishop cannot go out of the chessboard
  private static boolean valid(int x, int y, int N) {
    return x >= 0 && y >= 0 && x < N && y < N;
  }

  // Find minimum number of steps taken by the knight
  // from source to reach destination using BFS
  public static int BFS(Node src, Node dest, int N) {
    // set to check if matrix cell is visited before or not
    Set<Node> visited = new HashSet<>();

    // create a queue and enqueue first node
    Queue<Node> q = new ArrayDeque<>();
    q.add(src);

    // run till queue is not empty
    while (!q.isEmpty()) {
      // pop front node from queue and process it
      Node node = q.poll();

      int x = node.getX();
      int y = node.getY();
      int dist = node.getDist();

      // if destination is reached, return distance
      if (x == dest.getX() && y == dest.getY()) {
        return dist;
      }

      // Skip if location is visited before
      if (!visited.contains(node)) {
        // mark current node as visited
        visited.add(node);

        // check for all 4 possible movements for a knight
        // and enqueue each valid movement into the queue
        for (int i = 0; i < 4; ++i) {
          // Get the new valid position of Knight from current
          // position on chessboard and enqueue it in the
          // queue with +1 distance
          int x1 = x + row[i];
          int y1 = y + col[i];
        if (valid(x1, y1, N)) {
            q.add(new Node(x1, y1, dist + 1));
          }
        }
      }
    }

    // return minus if path is not possible
    return -1;
  }

  public static void main(String[] args) {
    String[] charArray = {"a", "b", "c", "d", "e", "f", "g", "h"};
    String[] numArray = {"1", "2", "3", "4", "5", "6", "7", "8"};
    int N = 8;
    Node src = new Node();
    Node dest = new Node();

    Scanner scanner = new Scanner(System.in);

    System.out.println("please enter the position of the bishop");
    String bishop = scanner.next();

    System.out.println("please enter the position you want to move");
    String bishopNext = scanner.next();

    for (int i = 0; i < charArray.length; i++) {
      if (bishop.substring(0, 1).equals(charArray[i])) {
        src.setX(i);
      }
      if (bishopNext.substring(0, 1).equals(charArray[i])) {
        dest.setX(i);
      }
    }

    for (int i = 0; i < numArray.length; i++) {
      if (bishop.substring(1).equals(numArray[i])) {
        src.setY(i);
      }
      if (bishopNext.substring(1).equals(numArray[i])) {
        dest.setY(i);
      }
    }

    int dist = BFS(src, dest, N);

    if (dist < 0) {
      System.out.println("cannot reach the destination");
    } else {
      System.out.println("total step that bishop have to take is: " + dist);
    }
  }
}
