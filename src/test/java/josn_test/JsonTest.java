package josn_test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.globalids.forms.AddApplication;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class JsonTest {
	ObjectMapper mapper = new ObjectMapper();
	String input_file = "src/test/resources/data.json";
	@Test
	public void json_list_reader_test() throws IOException {
		
		File json_file = new File(input_file);
		if(!json_file.exists()) {
			throw new FileNotFoundException("input file not found!");
		}
		
		List<AddApplication> applications = mapper.readValue(json_file, new TypeReference<List<AddApplication>>() {});
		applications.forEach(a-> System.out.println(a.getName()));
		
	}
}
