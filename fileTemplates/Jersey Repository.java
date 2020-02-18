#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#parse("File Header.java")

#set($class=${NAME}+"Repository")
#set($entityclass=$class.replace("Repository","Entity"))

import com.arcelormittal.ninasteel.nina_ui.domain.model.${entityclass};
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ${class} extends CrudRepository<${entityclass}, Integer>{
    List<${entityclass}> findAll();
    
    List<${entityclass}> findByIdIn(List<Integer> aListOfInts);
}