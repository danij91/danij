package part03_search;

import java.util.List;

public interface EmpDao {
	public List<EmployeeDTO> search(String data);
}
