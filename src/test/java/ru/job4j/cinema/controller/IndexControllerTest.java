package ru.job4j.cinema.controller;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class IndexControllerTest {

    @Test
    public void whenRequestGetIndexPageThenReturnIndexPage() {
        var indexController = new IndexController();

        var view = indexController.getIndex();

        assertThat(view).isEqualTo("index");
    }

}