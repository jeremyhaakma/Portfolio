<?php
/* This file contains the class Cart, which displays a number of items
 * available to purchase. Clicking 'buy' will add that item to the cart, 
 * clicking 'remove' removes it from the cart.
 * This file includes a session_start() and creates a new instance of the
 * class, so it will run on its own.
 * 
 * @author: Jeremy Haakma
 */
session_start();

class Cart
{
	var $products;
 
    /* Constructor 
     * Initialises $products variable to preset items, 
     * and calls the methods required to udpate and display
     * the shopping cart.
     * */
    public function Cart()
    {
        
        // ######## please do not alter the following code ########
       $products = array(
            array("name" => "Sledgehammer","price" => 125.75),
            array("name" => "Axe","price" => 190.50),
            array("name" => "Bandsaw","price" => 562.13),
            array("name" => "Chisel","price" => 12.9),
            array("name" => "Hacksaw", "price" => 18.45 )
        );
        // ##################################################
        //
        $this->products = $products;
        
        $this->updateItems();
		$this->printItems();
		$this->printCart();
        
     }//end constructor
        
        
   /* updateItems():
    * This method checks whether the user has clicked 'buy' or
    * 'remove', and updates the shopping cart accordingly. 
    */
	function updateItems(){
	
		if (!empty($_GET['product'])) {
			/* The $_GET value is a string composed of either "add_" or "rem_", 
			* followed by the name of the item. */
			$item = substr($_GET['product'], 4); //extracts product name from $_GET
			
			foreach ($this->products as $product) {
				
				/**** if an item was ADDED ****/
				if (("add_" . $product["name"]) == $_GET['product']) {
					$alreadyIn = false;
					/* See if it's already in the cart */
					for ($i = 0; $i < count($_SESSION['inCart']); $i++) {
						if ($_SESSION['inCart'][$i]["name"] == $item) {
							$_SESSION['inCart'][$i]["num"]++;//inc quantity
							$alreadyIn = true;
						}
					}
					/* First time adding */
					if (!$alreadyIn) {
						echo $_SESSION['inCart'][num];
						$_SESSION['inCart'][] = array(
							"name" => $product["name"],
							"price" => $product["price"],
							"num" => 1
						);
					}
				} //end if added
				
				/**** if item is REMOVED ****/
				else if (("rem_" . $product["name"]) == $_GET['product']) {
					
					for ($i = 0; $i < count($_SESSION['inCart']); $i++) {
						if ($_SESSION['inCart'][$i]["name"] == $item) {
							$_SESSION['inCart'][$i]["num"]--;
						}
					}
				}//end if
				
			} //end foreach
			
			/*Return to Cart.php so refresh doesn't add more products*/
			header("Location: /Cart.php");
		} //end if add product

	} //end updateItems()


	/* Prints the items available to purchase */
    function printItems(){
     /* Display items available to add to cart */
        echo "<b>Available items</b><br>";
        
        foreach ($this->products as $product) {
            echo $product["name"] . ": $" . number_format($product["price"], 2) 
            . " " . "<a href=\"Cart.php?product=" . urlencode("add_" . $product["name"]) 
            . "\">" . buy . "</a>" . "<br>";
        }
    }//end printItems()
    
    /* Prints the items currently in the cart.
     * When an item is reduced to 0 from a remove, it is not actually
     * removed from the array, so this function checks that the num is 
     * greater than 0 before printing.
     * */
	function printCart(){
		/* Display items in cart */
		echo "<br><b>Items in Cart</b><br>";
		$grandTotal = 0;
		foreach ($_SESSION['inCart'] as $product) {
			if ($product["num"] > 0) {//only if at least one item in cart
			// Name, price, quantity, total, remove_link
				$name = $product["name"];
				$price = $product["price"];
				$quantity = $product["num"];
				$total = $price * $quantity;
				
			
				echo $name . ": $" .
				 number_format($price, 2) 
				. " Quantity: " . $quantity 
				. " Total: $" .number_format($total, 2)
				. " " . "<a href=\"Cart.php?product=" 
				. urlencode("rem_" . $product["name"]) . "\">" . remove . "</a>" . "<br>";
				$grandTotal += $total;
			}
		}
		echo "<br><b>Total:</b> $" . number_format($grandTotal, 2);
	}//end printCart()
    
    
} //end Cart class

$cart = new Cart();

?>
