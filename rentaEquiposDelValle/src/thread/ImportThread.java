package thread;

import java.io.FileNotFoundException;
import java.io.IOException;

import model.RentaEquiposDelValle;

public class ImportThread {

private RentaEquiposDelValle rev;
	
	public ImportThread(RentaEquiposDelValle rev) {
		this.rev = rev;
	}
	
	public void run() throws IOException {
		try {
			rev.importDataClient();
			rev.importDataMachines();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}