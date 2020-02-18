#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#parse("File Header.java")

#set($class=${NAME}+"Service")
#set($repositoryclass=$class.replace("Service","Repository"))
#set($repositoryclassname=$repositoryclass.substring(0,1).toLowerCase()+$repositoryclass.substring(1))
#set($mapperclass=$class.replace("Service","Mapper"))
#set($mapperclassname=$mapperclass.substring(0,1).toLowerCase()+$mapperclass.substring(1))
#set($serviceclass=$class)
#set($serviceclassname=$serviceclass.substring(0,1).toLowerCase()+$serviceclass.substring(1))
#set($subject=$class.replace("Service",""))
#set($subjectname=$subject.substring(0,1).toLowerCase()+$subject.substring(1))
#set($api=$subject.toLowerCase())

import com.arcelormittal.ninasteel.nina_ui.domain.model.${subject}Entity;
import com.arcelormittal.ninasteel.nina_ui.repository.model.${repositoryclass};
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ${serviceclass} {
    private final ${repositoryclass} ${repositoryclassname};
    
    public ${serviceclass}(${repositoryclass} ${repositoryclassname}) {
        this.${repositoryclassname} = ${repositoryclassname};
    }

    public List<${subject}Entity> find${subject}sByIds(List<String> ids) {
        if (ids.isEmpty())
            return ${repositoryclassname}.findAll();
        else
            return ${repositoryclassname}.findByIdIn(ids.stream().map(Integer::valueOf).collect(Collectors.toList()));
    }

    public List<${subject}Entity> find${subject}sByIdsRestful(List<Integer> ids) {
            return ${repositoryclassname}.findByIdIn(ids);
    }

    public List<${subject}Entity> findAll() {
        return ${repositoryclassname}.findAll();
    }


    public Optional<${subject}Entity> findOne(Integer id) {
        return ${repositoryclassname}.findById(id);
    }

    public ${subject}Entity find${subject}ById(Integer id) {
        return ${repositoryclassname}.findById(id).orElseThrow(() -> new NotFoundException("not found ID: " + id));
    }

    public ${subject}Entity save${subject}Restful(${subject}Entity ${subjectname}) {
        return ${repositoryclassname}.save(${subjectname});
    }

    public ${subject}Entity update${subject}Restful(Integer id, ${subject}Entity ${subjectname}) {
        ${subjectname}.setId(id);

        return ${repositoryclassname}.save(${subjectname});
    }

    public void delete${subject}Restful(Integer id) {
        ${subject}Entity response = this.find${subject}ById(id);

        ${repositoryclassname}.delete(response);
    }

    public void delete${subject}Restful(List<Integer> ids) {
        for(Integer id : ids) {
            ${subject}Entity entity = this.find${subject}ById(id);
            ${repositoryclassname}.delete(entity);
        }
    }
}
