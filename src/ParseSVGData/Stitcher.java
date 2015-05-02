package ParseSVGData;

import java.util.ArrayList;

/**
 * Class to 'stitch' paths together. After conversion to SVG, one room may be composed of several paths.
 * <p>
 * This class has methods to stitch these paths, so each room is made up of one PathData object
 * 
 * @author Nick Killion
 */
public class Stitcher {
    private ArrayList<PathData> rawPaths;
    private ArrayList<PathData> stitchedPaths;
    private ArrayList<RoomNumberData> rnData;
    
    /**
     * Constructor for Stitcher
     * 
     * @param pd ArrayList of PathData objects representing rooms on a floor
     * @param rnData ArrayList of RoomNumberData objects
     */
    public Stitcher(ArrayList<PathData> pd, ArrayList<RoomNumberData> rnData) {
        rawPaths = pd;
        this.rnData = rnData;
        stitchedPaths = new ArrayList();
    }
    
    /**
     * Begins stitching paths. Base method for recursive stitch method
     * 
     * @return ArrayList representing stitched PathData
     */
    public ArrayList<PathData> stitch() {
        int i = 0;
        while (rawPaths.size() > 0) {
            PathData pd = rawPaths.get(i);
            innerStitch(pd);
        }
        return stitchedPaths;

    }

    /**
     * Stitch paths. Recursive part of algorithm
     * 
     * @param path1 The current path, will be compared to all other paths to see if stitching needs to take place
     */
    private void innerStitch(PathData path1) {
        int i, j, k;
        boolean needToStitch = false;
        boolean sameX = false;
        boolean sameY = false;
        boolean path1ContainsText = false;
        boolean path2ContainsText = false;
        double slope = 0;
        double yint = 0;

        PointData p1, p2, p3, p4;
        p1 = new PointData();
        p2 = new PointData();
        p3 = new PointData();
        p4 = new PointData();

        PathData path2 = new PathData();
        
        for (RoomNumberData rnd : rnData) {
            if (path1.contains(rnd.coords.x, rnd.coords.y))
                path1ContainsText = true;
        }

        i = -1;
        while (i < path1.points.size() - 1 && !needToStitch) {
            i++;
            sameX = false;
            sameY = false;
            p1 = path1.points.get(i);
            if (i == path1.points.size() - 1) {
                p2 = path1.points.get(0);
            } else {
                p2 = path1.points.get(i + 1);
            }

            //indicates that these 2 points form a horzontal line
            if (p1.closeY(p2)) {
                sameY = true;
            }
            //vertical
            if (p1.closeX(p2)) {
                sameX = true;
            }
            if (!sameX && !sameY) {
                slope = ((double) p1.y - p2.y) / ((double) p1.x - p2.x);
                yint = slope * (double) p1.x * -1 + p1.y;
            }

            //now check against everything else
            j = -1;
            while (j < rawPaths.size() - 1 && !needToStitch) {
                j++;
                path2 = rawPaths.get(j);
                path2ContainsText = false;
                
                for (RoomNumberData rnd : rnData) {
                    if (path2.contains(rnd.coords.x, rnd.coords.y))
                        path2ContainsText = true;
                }
                
                if (!path1.equals(path2) && !(path1ContainsText && path2ContainsText)) {
                    k = -1;
                    while (k < path2.points.size() - 1 && !needToStitch) {
                        k++;
                        p3 = path2.points.get(k);
                        if (k == path2.points.size() - 1) {
                            p4 = path2.points.get(0);
                        } else {
                            p4 = path2.points.get(k + 1);
                        }

                        if (sameX) {
                            if (p3.closeX(p4) && p2.closeX(p3)
                                    && path1.color.equals(path2.color)
                                    && checkLineOverlapX(p1, p2, p3, p4)) {
                                needToStitch = true;
                            }
                        } else if (sameY) {
                            if (p3.closeY(p4) && p2.closeY(p3)
                                    && path1.color.equals(path2.color)
                                    && checkLineOverlapY(p1, p2, p3, p4)) {
                                needToStitch = true;
                            }
                        } else {
                            double p3rhs = slope * p3.x + yint;
                            double p4rhs = slope * p4.x + yint;

                            if ((p3rhs > p3.y - 2 && p3rhs < p3.y + 2)
                                    && (p4rhs > p4.y - 2 && p4rhs < p4.y + 2)
                                    && path1.color.equals(path2.color)
                                    && checkLineOverlap(p1, p2, p3, p4)) {
                                needToStitch = true;
                            }
                        }
                    }
                }
            }
        }

        if (needToStitch) {
            PathData stitchedPath = stitchPaths(path1, p1, p2, path2, p3, p4);
            //rawPaths.set(rawPaths.indexOf(path1), stitchedPath);
            //rawPaths.remove(path2);
            rawPaths.remove(path1);
            rawPaths.remove(path2);
            innerStitch(stitchedPath);
        }
        else {
            rawPaths.remove(path1);
            if (!stitchedPaths.contains(path1));
                stitchedPaths.add(path1);
        }
    }
    
    /**
     * Checks whether line extent overlaps (If not, no stitching need take place)
     * 
     * @param p1 Point 1 of Path 1
     * @param p2 Point 2 of Path 1
     * @param p3 Point 1 of Path 2
     * @param p4 Point 2 of Path 2
     * @return true or false if the line is over lapping 
     */
    public boolean checkLineOverlap(PointData p1, PointData p2, PointData p3, PointData p4) {
        return checkLineOverlapX(p1, p2, p3, p4);
    }

    /**
     * Checks whether horizontal line extent overlaps (If not, no stitching need take place)
     * 
     * @param p1 Point 1 of Path 1
     * @param p2 Point 2 of Path 1
     * @param p3 Point 1 of Path 2
     * @param p4 Point 2 of Path 2
     * @return true or false if the line is overlapping 
     */
    public boolean checkLineOverlapY(PointData p1, PointData p2, PointData p3, PointData p4) {
        int path1MaxX = p1.x;
        int path1MinX = p2.x;
        int path2MaxX = p3.x;
        int path2MinX = p4.x;

        if (p2.x > p1.x) {
            path1MaxX = p2.x;
            path1MinX = p1.x;
        }
        if (p4.x > p3.x) {
            path2MaxX = p4.x;
            path2MinX = p3.x;
        }

        if (path1MinX >= path2MaxX || path1MaxX <= path2MinX) {
            return false;
        }
        return true;
    }

    /**
     * Checks whether vertical line extent overlaps (If not, no stitching need take place)
     * 
     * @param p1 Point 1 of Path 1
     * @param p2 Point 2 of Path 1
     * @param p3 Point 1 of Path 2
     * @param p4 Point 2 of Path 2
     * @return true or false if the line is overlapping
     */
    public boolean checkLineOverlapX(PointData p1, PointData p2, PointData p3, PointData p4) {
        int path1MaxY = p1.y;
        int path1MinY = p2.y;
        int path2MaxY = p3.y;
        int path2MinY = p4.y;

        if (p2.y > p1.y) {
            path1MaxY = p2.y;
            path1MinY = p1.y;
        }
        if (p4.y > p3.y) {
            path2MaxY = p4.y;
            path2MinY = p3.y;
        }

        if (path1MinY >= path2MaxY || path1MaxY <= path2MinY) {
            return false;
        }
        return true;
    }

    /**
     * Performs the actual 'stitching' of paths, after needToStitch is determined
     * 
     * @param path1 PathData for first path that needs stitching
     * @param p1 PointData for point 1 of path1's incident edge
     * @param p2 PointData for point 2 of path1's incident edge
     * @param path2 PathData for second path that needs stitching
     * @param p3 PointData for point 1 of path2's incident edge
     * @param p4 PointData for point 2 of path2's incident edge
     * @return true or false if the line is overlapping
     */
    private PathData stitchPaths(PathData path1, PointData p1, PointData p2,
            PathData path2, PointData p3, PointData p4) {

        ArrayList<PointData> newPoints = new ArrayList();
        newPoints.add(p1);
        int i = path2.points.indexOf(p4);

        //iterate through path2
        while (i != path2.points.indexOf(p3)) {
            if (!newPoints.contains(path2.points.get(i)))
                newPoints.add(path2.points.get(i));

            i++;
            if (i == path2.points.size()) {
                i = 0;
            }
        }
        if (!newPoints.contains(p3))
            newPoints.add(p3);
        i = path1.points.indexOf(p2);

        //iterate through path1
        while (i != path1.points.indexOf(p1)) {
            if (!newPoints.contains(path1.points.get(i)))
                newPoints.add(path1.points.get(i));

            i++;
            if (i == path1.points.size()) {
                i = 0;
            }
        }
        return new PathData(newPoints, path1.color, false);

    }

}
