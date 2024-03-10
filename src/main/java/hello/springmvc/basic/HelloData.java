package hello.springmvc.basic;

import lombok.Data;

@Data // @getter @setter @TOstring @EqualsAndHashCode @RequestArgsConstructor를 자동적으로 적용해줌
public class HelloData {
    private String username;
    private int age;
}
