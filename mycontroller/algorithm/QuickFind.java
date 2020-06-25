package mycontroller.algorithm;

import utilities.Coordinate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Team Number: WS6-6D
 * Group member: Yue Peng, Zhicheng Hu, Tian Qiu
 *
 * @create 24-10-2019 21:14:31
 * description: implement quickFind algorithm(whether there is a path can connect two point directly)
 **/

public class QuickFind {
    /**class to store the result
     */
    public class Result {
        /**
         * get the componentMark of the coordinate
         * @param coordinate the specific coordinate
         * @return the component mark of the coordinate
         */
        public int getComponentMark(Coordinate coordinate) {
            return mark[coordinate.x - scope.getMinX()][coordinate.y - scope.getMinY()];
        }

        /** the component count of the mark
         *
         * @param mark the specific mark
         * @return the component count of the mark
         */
        public int getComponentCount(int mark) {
            return connectedComponents.get(mark);
        }

    }


    private Rect scope;
    private int[][] mark;
    private List<Integer> connectedComponents;
    private QuickFindStrategy impl;

    /**the construction of QuickFind
     *
     * @param scope the Rect of QuickFind
     * @param impl the QuickFindStrategy of QuickFind
     */
    public QuickFind(Rect scope, QuickFindStrategy impl){
        this.scope = scope;
        mark = new int[scope.getWidth()][scope.getHeight()];
        connectedComponents = new ArrayList<>();
        this.impl = impl;
    }

    /**run the QuickFind Algorithm
     *
     * @return the result of QuickFind
     */
    public Result run() {
        Result result = new Result();

        int component = 1;
        for (int x = 0; x < mark.length; x++) {
            for (int y = 0; y < mark[0].length; y++) {
                Coordinate coordinate = new Coordinate(x + scope.getMinX(), y + scope.getMinY());
                if(impl.isConnected(coordinate) && mark[coordinate.x - scope.getMinX()][coordinate.y - scope.getMinY()] == 0) {
                    bfs(x, y, component);
                    component++;

                }
            }
        }

        return result;
    }

    /*implement bfs algorithm
     *
     * @param x x-coordinate
     * @param y y-coordinate
     * @param component connected component
     */
    private void bfs(int x, int y, int component) {
        Queue<Coordinate> bfsQueue = new LinkedList<>();

        mark[x][y] = component;
        bfsQueue.offer(new Coordinate(x, y));
        connectedComponents.add(1);

        while(!bfsQueue.isEmpty()) {
            Coordinate coordinate0 = bfsQueue.poll();

            int count =  connectedComponents.get(component-1);

            for (int dx = -1; dx < 2; dx+= 2) {
                Coordinate coordinate1 = new Coordinate(coordinate0.x + scope.getMinX() + dx, coordinate0.y + scope.getMinY());
                if(scope.contains(coordinate1) && impl.isConnected(coordinate1) &&
                        mark[coordinate1.x - scope.getMinX()][coordinate1.y - scope.getMinY()] == 0) {
                    mark[coordinate1.x - scope.getMinX()][coordinate1.y - scope.getMinY()] = component;
                    bfsQueue.offer(coordinate1);
                    count++;
                }
            }

            for (int dy = -1; dy < 2; dy+=2) {
                Coordinate coordinate1 = new Coordinate(coordinate0.x + scope.getMinX(), coordinate0.y + scope.getMinY() + dy);
                if(scope.contains(coordinate1) && impl.isConnected(coordinate1) &&
                        mark[coordinate1.x - scope.getMinX()][coordinate1.y - scope.getMinY()] == 0) {
                    mark[coordinate1.x - scope.getMinX()][coordinate1.y - scope.getMinY()] = component;
                    bfsQueue.offer(coordinate1);
                    count++;
                }
            }

            connectedComponents.set(component-1, count);

        }

    }


}
