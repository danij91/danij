package part05;

import java.util.List;

public interface MemDAO {
	public List<MemDTO> listMethod();
	public void insertMethod(MemDTO dto);
}
