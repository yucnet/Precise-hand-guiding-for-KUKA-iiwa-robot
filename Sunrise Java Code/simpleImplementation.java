package lbrExampleApplications;


import static com.kuka.roboticsAPI.motionModel.BasicMotions.circ;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.lin;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.linRel;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.ptp;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.ptpHome;

import com.kuka.generated.ioAccess.MediaFlangeIOGroup;
import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import com.kuka.roboticsAPI.applicationModel.tasks.RoboticsAPITask;
import com.kuka.roboticsAPI.applicationModel.tasks.UseRoboticsAPIContext;
import com.kuka.roboticsAPI.conditionModel.ICondition;
import com.kuka.roboticsAPI.controllerModel.Controller;
import com.kuka.roboticsAPI.deviceModel.LBR;
import com.kuka.roboticsAPI.geometricModel.Frame;
import com.kuka.roboticsAPI.geometricModel.math.Transformation;

/**
 * Mohammad SAFEEA
 * 
 * University: Coimbra, Ensam.
 * 
 * 27th-september-2017
 *  
 * This is an example on how to implement the precise 
 * hand guiding class into your application.
 * 
 * The precise handguiding class uses the direct servo function
 * make sure to load import it into your project.
 * 
 * 
 */

public class simpleImplementation extends RoboticsAPIApplication {
	private Controller kuka_Sunrise_Cabinet_1;
	private LBR _lbr;
	
	public MediaFlangeIOGroup daIO;

	@Override
	public void initialize() 
	{
		kuka_Sunrise_Cabinet_1 = getController("KUKA_Sunrise_Cabinet_1");
		_lbr = (LBR) getDevice(kuka_Sunrise_Cabinet_1,
				"LBR_iiwa_7_R800_1");
		
		daIO = new MediaFlangeIOGroup(kuka_Sunrise_Cabinet_1);
		
	}
	

    private void moveToInitialPosition1()
    {
    	_lbr.move(
        		ptp(0., -Math.PI / 180 * 10., 0., -Math.PI / 180 * 100., Math.PI / 180 * 0.,
                        Math.PI / 180 * 90., 0.).setJointVelocityRel(0.15));
    }
    

	@Override
	public void run() {
		moveToInitialPosition1();
		try
		{
			String s=PreciseHandGuidingForUpload1.LBRiiwa7R800;
			PreciseHandGuidingForUpload1.
			HandGuiding(_lbr, kuka_Sunrise_Cabinet_1,s);
		
		}
		catch (Exception e) {
			// TODO: handle exception
			getLogger().error(e.toString());
		}
			
		}

	/**
	 * Auto-generated method stub. Do not modify the contents of this method.
	 */
	public static void main(String[] args) {
		simpleImplementation app = new simpleImplementation();
		app.runApplication();
	}
}
