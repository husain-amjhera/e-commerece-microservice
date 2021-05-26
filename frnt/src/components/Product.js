import React, { useState } from "react";
import { useDispatch } from "react-redux";
import "./Product.css";
import { addToCart, addToWishList } from "../actions/productActions";
import { useSelector } from "react-redux";
import { Tooltip } from "@material-ui/core";

function Product({ key, id, name, imageUrl, price, shoppingCartId }) {
  const currentUser = useSelector(state => state.currentUser);
  const dispatch = useDispatch();
  const [quantity, setQuantity] = useState(1);
  const handleAddToWishList = () => {
    dispatch(
      addToWishList({
        inventoryId: id,
        userAccountId: currentUser.userId
      })
    );
  };
  const handleAddToCart = () => {
    dispatch(
      addToCart({
        inventoryId: id,
        quantity: quantity,
        shoppingCartId: shoppingCartId
      })
    );
  };

  return (
    <div className="product mb-4">
      <div className="card mx-2">
        <span
          onClick={handleAddToWishList}
          className="btn btn-large"
          style={{
            display: "inline-block",
            marginLeft: "auto",
            color: "maroon"
          }}
        >
          <Tooltip title="Add to wishlist">
            <i className="fas fa-heart fa-lg"></i>
          </Tooltip>
        </span>
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
            <select
              className="ml-1 quantity-select"
              value={quantity}
              onChange={e => setQuantity(e.target.value)}
              defaultValue={quantity}
            >
              <option value="1">1</option>
              <option value="2">2</option>
            </select>
            <button
              className="btn btn-primary ml-auto mr-2 "
              onClick={handleAddToCart}
            >
              Add to Cart
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Product;
