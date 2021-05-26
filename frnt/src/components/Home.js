import React, { useState } from "react";
import "./Home.css";
import { useSelector } from "react-redux";
import { useEffect } from "react";
import { useDispatch } from "react-redux";
import Product from "./Product";
import {
  getCart,
  getAddress,
  getOrders,
  getWishList,
  getProducts
} from "../actions/productActions";
import { getCards } from "../actions/userActions";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { GET_ERRORS } from "../actions/type";

function Home() {
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(getCart(currentUser?.shoppingCartId));
    dispatch(getCards(currentUser?.userId));
    dispatch(getAddress(currentUser?.userId));
    dispatch(getOrders(currentUser?.userId));
    dispatch(getWishList(currentUser?.userId));
  }, []);
  const products = useSelector(state => state.products);
  // const cart = useSelector(state => state.cart);
  // console.log(cart);
  const errors = useSelector(state => state.errors);
  useEffect(() => {
    errors.message &&
      toast.info(errors.message, { autoClose: 3000, hideProgressBar: true });
    dispatch({
      type: GET_ERRORS,
      payload: {}
    });
  }, [dispatch, errors.message]);
  const currentUser = useSelector(state => state.currentUser);
  const previousPage = () => {
    dispatch(getProducts(products.pageNumber - 1));
  };

  const nextPage = () => {
    dispatch(getProducts(products.pageNumber + 1));
  };

  return (
    <div className="home">
      <ToastContainer position="top-right" />
      <img
        className="home__image"
        src="https://images-eu.ssl-images-amazon.com/images/G/02/digital/video/merch2016/Hero/Covid19/Generic/GWBleedingHero_ENG_COVIDUPDATE__XSite_1500x600_PV_en-GB._CB428684220_.jpg"
        alt=""
      />
      <div className="row mt-4">
        {products?.list?.map(product => (
          <div className="col-3">
            <Product
              key={product.key}
              id={product.id}
              name={product.title}
              imageUrl={product.imageUrl}
              price={product.price}
              shoppingCartId={currentUser.shoppingCartId}
            />
          </div>
        ))}
      </div>
      <div className="row mb-5">
        <button
          className="btn btn-light mr-auto ml-2"
          style={{ backgroundColor: "#79CBE8" }}
          disabled={products.pageNumber === 0 ? true : false}
          onClick={previousPage}
        >
          Previous
        </button>
        <span
          className="mx- mt-3"
          style={{ fontWeight: "500", fontSize: "20px" }}
        >
          Showing Page {products.pageNumber + 1} of {products.totalPages}
        </span>
        <button
          className="btn btn-light ml-auto mr-2"
          style={{ backgroundColor: "#79CBE8" }}
          disabled={
            products.pageNumber === products.totalPages - 1 ? true : false
          }
          onClick={nextPage}
        >
          Next
        </button>
      </div>
    </div>
  );
}

export default Home;
