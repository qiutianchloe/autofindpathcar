package mycontroller.algorithm;

import utilities.Coordinate;


import java.util.*;

/**
 * Team Number: WS6-6D
 * Group member: Yue Peng, Zhicheng Hu, Tian Qiu
 *
 * @create 24-10-2019 21:14:31
 * description: implement AstarAlgorithm to find out the movement track
 **/

public class AstarAlgorithm {
    /**class to store search result*/
    public class Result {
        private boolean success;

        /**
         * return success attribute of Result
         * @return  success
         */
        public boolean isSuccess() {
            return success;
        }

        /**get target's total weight
         *
         * @return total weight of target
         */
        public float getTotalWeight() {
            return lookup.get(target).totalWeight;
        }

        /**get the result route
         *
         * @return the list of coordinate which shows the movement route
         */
        public List<Coordinate> getRoute() {
            if(!success) return null;
            LinkedList<Coordinate> result = new LinkedList<>();
            Coordinate curPos = target;
            while(curPos != null && curPos != start) {
                result.addFirst(curPos);
                curPos = lookup.get(curPos).from;
            }
            result.addFirst(start);
            return result;
        }
    }

    //point structure aims to meet the requirement of for A* Algorithm
    class Node implements Comparable<Node> {
        private Coordinate coordinate;
        private Coordinate from;
        private float weight;
        private float totalWeight;
        private float distance;

        private Node(Coordinate coordinate) {
            this.coordinate = coordinate;
            weight = impl.getWeight(coordinate);
            distance = Math.abs(coordinate.x - target.x) + Math.abs(coordinate.y - target.y);
            totalWeight = Float.MAX_VALUE/2;
        }

        @Override
        public int compareTo(Node o) {
            return (this.distance + this.weight) - (o.distance + o.weight) < 0 ? -1: 1;
        }
    }

    private Coordinate start;
    private Coordinate target;
    private AstarStrategy impl;
    private PriorityQueue<Node> pq;
    private Map<Coordinate, Node> lookup;

    /**the construction of AstarAlgorithm
     *
     * @param start the start coordinate
     * @param target the target coordinate
     * @param impl  the AstarStrategy
     */
    public AstarAlgorithm(Coordinate start, Coordinate target, AstarStrategy impl) {
        this.start = start;
        this.target = target;
        this.impl = impl;
        pq = new PriorityQueue<>();
        lookup = new HashMap<>();
    }

    /**run the A* Algorithm and return the search result
     *
     * @return the search result of A*
     */
    public Result run() {

        Result result = new Result();
        Node startNode = new Node(start);
        startNode.totalWeight = 0;
        pq.offer(startNode);
        lookup.put(start, startNode);

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            for (Coordinate coordinate: impl.getAdjacentCoord(node.coordinate)) {

                Node adjNode;
                boolean isNew = false;
                if(lookup.containsKey(coordinate)) {
                    adjNode = lookup.get(coordinate);
                } else {
                    adjNode = new Node(coordinate);
                    isNew = true;
                }

                if(adjNode.totalWeight > adjNode.weight + node.totalWeight) {
                    adjNode.totalWeight = adjNode.weight + node.totalWeight;
                    adjNode.from = node.coordinate;
                }

                if(isNew) {
                    pq.offer(adjNode);
                    lookup.put(coordinate, adjNode);
                }
                //if it arrives the target coordinate, stop searching
                if(coordinate.equals(target)) {
                    result.success = true;
                    break;
                }
            }
        }

        return result;
    }



}
