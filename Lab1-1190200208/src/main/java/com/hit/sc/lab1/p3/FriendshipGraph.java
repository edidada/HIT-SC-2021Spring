package com.hit.sc.lab1.p3;

import java.util.*;

public class FriendshipGraph {
    public List<Person> persons = new ArrayList<Person>();
    private final Set<String> names = new HashSet<String>();
    public Vector<Vector<Integer>> relationships = new Vector<Vector<Integer>>();


    public boolean addVertex(Person p) {
        if (names.contains(p.getName())) {
            System.out.println("此人已存在！");
            return false;
        } else {
            Vector<Integer> singlePerson = new Vector<Integer>();
            Vector<Integer> temp = new Vector<Integer>();
            p.setIndex(persons.size());
            persons.add(p);
            names.add(p.getName());
            relationships.add(singlePerson);
            for (int i = 0; i < p.getIndex(); i++) {
                temp = relationships.elementAt(i);
                temp.add(0);
            }
            temp = relationships.elementAt(p.getIndex());
            for (int i = 0; i <= p.getIndex(); i++) {
                temp.add(0);
            }
            return true;
        }
    }

    public boolean addEdge(Person p1, Person p2) {
        if (p1.getIndex() == -1) {
            System.out.println(p1.getName() + "不在关系图中");
            return false;
        }
        if (p2.getIndex() == -1) {
            System.out.println(p2.getName() + "不在关系图中");
            return false;
        }
        Vector<Integer> temp = relationships.elementAt(p1.getIndex());
        temp.set(p2.getIndex(), 1);
        return true;
    }

    public int getDistance(Person p1, Person p2) {
        if (p1.getIndex() == -1) {
            System.out.print(p1.getName() + "不在关系图中");
            return -1;
        }
        if (p2.getIndex() == -1) {
            System.out.print(p2.getName() + "不在关系图中");
            return -1;
        }
        if (p1.getIndex() == p2.getIndex()) {
            return 0;
        }
        Queue<Person> queue = new LinkedList<Person>();
        int distance = 0;
        Person temp = new Person("");
        Person queueEnd = new Person("");
        Vector<Integer> tempCol = new Vector<Integer>();
        int[] visit = new int[persons.size()];
        boolean[] isQueueEnd = new boolean[persons.size()];


        queue.add(p1);
        visit[p1.getIndex()] = 1;
        isQueueEnd[p1.getIndex()]=true;

        while (queue.peek() != p2) {
            temp = queue.poll();
            tempCol = relationships.get(temp.getIndex());
            for (int i = 0; i < tempCol.size(); i++) {
                if (tempCol.get(i) == 1) {
                    for (Person t : persons) {
                        if (t.getIndex() == i && visit[i] == 0) {
                            queue.add(t);
                            visit[i] = 1;
                            queueEnd = t;
                            break;
                        }
                    }
                }
            }

            if (queue.isEmpty())
                return -1;

            if (isQueueEnd[temp.getIndex()]) {
                isQueueEnd[queueEnd.getIndex()]=true;
                distance++;
            }
        }
        return distance;
    }

    public static void main(String[] args) {
        FriendshipGraph graph = new FriendshipGraph();
        Person rachel = new Person("Rachel");
        Person ross = new Person("Ross");
        Person ben = new Person("Ben");
        Person kramer = new Person("Kramer");
        graph.addVertex(rachel);
        graph.addVertex(ross);
        graph.addVertex(ben);
        graph.addVertex(kramer);
        graph.addEdge(rachel, ross);
        graph.addEdge(ross, rachel);
        graph.addEdge(ross, ben);
        graph.addEdge(ben, ross);
        System.out.println(graph.getDistance(rachel, ross));
        System.out.println(graph.getDistance(rachel, ben));
        System.out.println(graph.getDistance(rachel, rachel));
        System.out.println(graph.getDistance(rachel, kramer));
    }
}
