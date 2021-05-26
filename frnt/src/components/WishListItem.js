import React, { useState } from "react";
import { useDispatch } from "react-redux";
import "./Product.css";
import {
  addToCart,
  addToWishList,
  removeFromWishList
} from "../actions/productActions";
import { useSelector } from "react-redux";

function WishListItem({ key, id, name, imageUrl, price }) {
  const currentUser = useSelector(state => state.currentUser);
  const dispatch = useDispatch();
  const handleremoveFromWishList = () => {
    console.log({
      userAccountId: currentUser.userId,
      inventoryId: id
    });
    dispatch(removeFromWishList(currentUser.userId, id));
  };
  const handleAddToCart = () => {
    dispatch(
      addToCart({
        inventoryId: id,
        quantity: 1,
        shoppingCartId: currentUser.shoppingCartId
      })
    );
    handleremoveFromWishList();
  };

  return (
    <div className="product mb-4">
      <div className="card mx-2">
        <img
          className="card-img-top mt-3"
          src={imageUrl}
          height="200px"
          width="200px"
          alt={name}
        />
        <div className="card-body">
          <h4 className="card-title">{name}</h4>
          <h5 className="card-text">Rs. {price}</h5>
          <div className="row">
            <button
              className="btn btn-warning ml-auto mr-1 "
              onClick={handleremoveFromWishList}
            >
              Remove
            </button>
            <button className="btn btn-primary mr-2 " onClick={handleAddToCart}>
              Add to Cart
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default WishListItem;
