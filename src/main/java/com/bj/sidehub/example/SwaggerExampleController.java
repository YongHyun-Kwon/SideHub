package com.bj.sidehub.example;

import io.swagger.annotations.ApiOperation;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Entity
@Getter
@Setter
class Example {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
} // entity

interface SwaggerExampleRepository extends CrudRepository<Example, Long> {}

@RestController
@RequestMapping("/example")
@RequiredArgsConstructor
public class SwaggerExampleController {

    private final SwaggerExampleRepository exampleRepository;

    @GetMapping("/")
    @ApiOperation(value = "예제목록 조회", notes = "전체 예제목록을 조회한다.")
    public List<Example> getExamples() {
        return (List<Example>) exampleRepository.findAll();
    } // getExamples

    @PostMapping("/")
    @ApiOperation(value = "예제 생성", notes = "예제를 생성한다.")
    public Example createExample(@RequestBody Example example) {
        return exampleRepository.save(example);
    } // createExample

    @GetMapping("/{id}")
    @ApiOperation(value = "예제 상세 조회", notes = "예제 상제를 조회한다.")
    public Example getExample(@PathVariable Long id) {
        return exampleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid example id: " + id));
    } // getExample

} // class
