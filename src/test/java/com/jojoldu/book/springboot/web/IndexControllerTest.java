package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.domain.posts.Posts;
import com.jojoldu.book.springboot.domain.posts.PostsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IndexControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @Test
    public void 메인페이지_로딩() {
        // when
        String body = this.restTemplate.getForObject("/", String.class);

        // then
        assertThat(body).contains("스프링 부트로 시작하는 웹 서비스");
    }

    @Test
    public void 등록페이지_로딩() {
        // when
        String body = this.restTemplate.getForObject("/posts/save", String.class);

        // then
        assertThat(body).contains("게시글 등록");
    }

    @Test
    public void 수정페이지_로딩() {
        // given
        Posts posts  = postsRepository.save(Posts.builder().title("title").content("content").author("author").build());

        // when
        String body = this.restTemplate.getForObject("/posts/update/" + posts.getId(), String.class);

        // then
        assertThat(body).contains("게시글 수정");
    }
}
