package thread;

import java.io.FileNotFoundException;

import model.RentaEquiposDelValle;

public class ExportThread {
	
	private RentaEquiposDelValle rev;
	
	public ExportThread(RentaEquiposDelValle rev) {
		this.rev = rev;
	}
	
	public void run() {
		try {
			rev.exportDataClients();
			rev.exportDataMachines();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}