package simu.model;

public class Sqlmain {
	public static void main(String[] args) throws Exception {
		Database dao = new Database();
		dao.readDataBase();
	}
}
