import React from "react";
import "./Cart.css";
import CartItem from "./CartItem";
import Subtotal from "./Subtotal";
import Product from "./Product";
import { useDispatch } from "react-redux";
import { useSelector } from "react-redux";
import { useEffect } from "react";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { GET_ERRORS } from "../actions/type";

function Cart() {
  const dispatch = useDispatch();
  const currentUser = useSelector(state => state.currentUser);
  const basket = useSelector(state => state.cart);
  const products = useSelector(state => state.products.list);
  const product = products[Math.floor(Math.random() * products.length)];
  const errors = useSelector(state => state.errors);
  useEffect(() => {
    errors.message &&
      toast.info(errors.message, { autoClose: 3000, hideProgressBar: true });
    dispatch({
      type: GET_ERRORS,
      payload: {}
    });
  }, [dispatch, errors.message]);
  return (
    <div className="cart">
      <div className="checkout">
        <ToastContainer position="top-right" />
        <div className="checkout__left">
          {basket?.cartDetails?.length === 0 ? (
            <div
              style={{
                display: "grid",
                objectFit: "contain",
                height: "85vh"
              }}
            >
              <img
                className="mx-auto"
                src="https://i.pinimg.com/originals/2e/ac/fa/2eacfa305d7715bdcd86bb4956209038.png"
                alt="Cart Empty"
              />
            </div>
          ) : (
            <div>
              <h2 className="checkout__title mb-3">Your Shopping Basket</h2>
              {basket.cartDetails.map(item => (
                <CartItem
                  key={item.cartDetailId}
                  cartDetailId={item.cartDetailId}
                  inventoryId={item.inventoryId}
                  title={item.title}
                  price={item.price}
                  imageUrl={item.imageUrl}
                  shoppingCartId={basket.cartId}
                  quantity={item.quantity}
                  tax={item.tax}
                />
              ))}
            </div>
          )}
        </div>
        {basket?.cartDetails?.length > 0 && (
          <div className="checkout__right">
            <div className="checkout__right__ad">
              <img
                alt="ad"
                src="https://images-eu.ssl-images-amazon.com/images/G/31/checkout/assets/TM_desktop._CB443006202_.png"
              />
            </div>
            <Subtotal cartItemList={basket.cartDetails} />
            <p className="mt-5">Sponsored</p>
            <hr />
            <div
              style={{
                width: "inherit",
                marginTop: "40px",
                maxWidth: "350px",
                marginRight: "auto",
                marginLeft: "auto"
              }}
            >
              <Product
                key={product.key}
                id={product.id}
                name={product.title}
                imageUrl={product.imageUrl}
                price={product.price}
                shoppingCartId={currentUser.shoppingCartId}
              />
            </div>
          </div>
        )}
      </div>
    </div>
  );
}

export default Cart;
