package de.mpicbg.haase.xwingedfreconstruction.scijava.plugins.edf;

import de.mpicbg.haase.xwingedfreconstruction.scijava.plugins.internal.*;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.img.Img;
import net.imglib2.type.numeric.RealType;
import org.scijava.ItemIO;
import org.scijava.command.Command;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;

/**
 * Author: Robert Haase (http://haesleinhuepf.net) at MPI CBG (http://mpi-cbg.de)
 * November 2017
 */

@Plugin(type = Command.class, menuPath = "XWing>EDF>Max Entropy (DCTS2D) projection")
public class MaxEntropyDCTS2DProjectionPlugin<T extends RealType<T>> extends
                                                                     AbstractMaxProjectionPlugin<T> implements Command
{
  @Parameter protected int focusedRegionRadius = 5;

  @Parameter protected double samplingFactor = 0.5;

  @Parameter private Img<T> input;

  //@Parameter private UIService uiService;

  @Parameter(type = ItemIO.OUTPUT) RandomAccessibleInterval output;

  @Override public void run()
  {
    output = project(input,
                     samplingFactor,
                     DCTS2DPerPixelPlugin.process(resampleStackSliceBySlice(input,
                                                                    samplingFactor),
                                                             focusedRegionRadius));
  }

}