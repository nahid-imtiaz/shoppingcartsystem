package edu.miu.shoppingcartsystem;

import edu.miu.shoppingcartsystem.model.CartItem;
import edu.miu.shoppingcartsystem.repository.CartItemRepository;
import edu.miu.shoppingcartsystem.service.CartItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@SpringBootTest
class ShoppingcartsystemApplicationTests {
	@Autowired
	private CartItemService cartItemService;

	@MockBean
	private CartItemRepository cartItemRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void getCartItemById(){
		int id = 1;
		//when(cartItemRepository.findById(id)).thenReturn(Stream.of(new CartItem()))
	}

}
