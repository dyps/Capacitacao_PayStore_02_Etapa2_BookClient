package br.com.bookclient.book.service;

import java.util.UUID;

import br.com.bookclient.book.BookDTO;

@FunctionalInterface
public interface GetBookByUUIDService {

	BookDTO find(UUID uuid);

}
