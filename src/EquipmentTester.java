import java.time.LocalDate;
import controller.ListDetailsHelper;
import model.ListDetails;
import model.Studio;

/**
 * @author Tom Sorteberg - tsorteberg
 * CIS175 - Spring 2021
 * Mar 4, 2021
 */
public class EquipmentTester {

	public static void main(String[] args) {
		
		Studio studio = new Studio("Cambridge");
		
		ListDetailsHelper ldh = new ListDetailsHelper();

		ListDetails studiolist = new ListDetails("Cambridge Studio", LocalDate.now(), studio);

		ldh.insertNewListDetails(studiolist);

	}

}
