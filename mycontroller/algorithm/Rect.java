package mycontroller.algorithm;

import utilities.Coordinate;
/**
 * Team Number: WS6-6D
 * Group member: Yue Peng, Zhicheng Hu, Tian Qiu
 *
 * @create 24-10-2019 21:14:31
 * description: class that stores the boundary
 **/

public class Rect {
    private int minX;
    private int minY;
    private int maxX;
    private int maxY;

    /**construction of Rect
     *
     * @param minX  minX of Rect
     * @param minY  minY of Rect
     * @param maxX  maxX of Rect
     * @param maxY  maxY of Rect
     */
    public Rect(int minX, int minY, int maxX, int maxY) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    /**get minX
     *
     * @return this.minX
     */
    public int getMinX() {
        return minX;
    }
    /**get minY
     *
     * @return this.minY
     */
    public int getMinY() {
        return minY;
    }
    /**get MaxX
     *
     * @return this.maxX
     */
    public int getMaxX() {
        return maxX;
    }
    /**get MaxY
     *
     * @return this.maxY
     */
    public int getMaxY() {
        return maxY;
    }
    /**get Width between maxX and minX
     *
     * @return width
     */
    public int getWidth() {
        return maxX - minX + 1;
    }
    /**get Height between maxY and minY
     *
     * @return height
     */
    public int getHeight() {
        return maxY - minY + 1;
    }

    /**check whether the coordinate is within the boundary
     *
     * @param coordinate the coordinate which is needed to cheek
     * @return true if the point is within the boundary, false if it is not
     */
    public boolean contains(Coordinate coordinate) {
        int x = coordinate.x, y = coordinate.y;
        return x >= minX && x <= maxX && y >= minY && y <= maxY;
    }

    /**check whether the coordinate is at the boundary
     *
     * @param coordinate the coordinate which is needed to check
     * @return  true if it is at boundary, false if it is not
     */
    public boolean atBoundary(Coordinate coordinate) {
        int x = coordinate.x, y = coordinate.y;
        return x == minX || x == maxX || y == minY || y == maxY;
    }

}
