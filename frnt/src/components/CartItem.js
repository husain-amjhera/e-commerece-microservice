import "./CartItem.css";
import { useDispatch } from "react-redux";
import { useSelector } from "react-redux";
import {
  removeItemFromCart,
  addToWishList,
  updateCart
} from "../actions/productActions";
import { useState } from "react";

function CartItem({
  cartDetailId,
  inventoryId,
  quantity,
  title,
  price,
  imageUrl,
  shoppingCartId,
  tax
}) {
  const option = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
  const dispatch = useDispatch();
  const currentUser = useSelector(state => state.currentUser);
  const handleAddToWishList = () => {
    dispatch(
      addToWishList({
        inventoryId: inventoryId,
        userAccountId: currentUser.userId
      })
    );
    remove();
  };
  const remove = () => {
    dispatch(removeItemFromCart(shoppingCartId, cartDetailId));
  };

  const handleCartUpdate = e => {
    dispatch(
      updateCart({
        cartDetailId,
        inventoryId,
        quantity: e.target.value,
        title,
        price,
        imageUrl,
        shoppingCartId,
        tax
      })
    );
  };
  return (
    <>
      <div className="checkoutProduct mb-3">
        <img className="checkoutProduct__image mr-5" src={imageUrl} alt="" />
        <div className="checkoutProduct__info row">
          <div className="col-9">
            <p
              className="checkoutProduct__title"
              style={{ marginRight: "260px", width: "400px" }}
            >
              {title}
            </p>
            <span
              style={{
                color: " #007600",
                fontSize: "25px",
                lineHeight: " 16px"
              }}
            >
              In stock
            </span>
            <p className="mt-1">
              Quantity:&nbsp;
              <select value={quantity} onChange={e => handleCartUpdate(e)}>
                {option.map(value => (
                  <option value={value}>{value}</option>
                ))}
                {/*
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
                <option value="6">6</option>
                <option value="7">7</option>
                <option value="8">8</option>
                <option value="9">9</option>
                <option value="10">10</option> */}
              </select>
            </p>
          </div>
          <div className="col-3">
            <p className="checkoutProduct__price mt-2 ">Price: ₹ {price}</p>
            <p className="checkoutProduct__price mt-2">Tax: {tax}%</p>
            {
              <button
                style={{ paddingLeft: "33px", paddingRight: "33px" }}
                onClick={remove}
              >
                Remove
              </button>
            }
            {<button onClick={handleAddToWishList}>Add to WishList</button>}
          </div>
        </div>
      </div>
    </>
  );
}

export default CartItem;
