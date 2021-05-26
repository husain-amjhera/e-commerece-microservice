import Axios from "axios";
import {
  BASE_URL,
  GET_PRODUCT,
  ADD_TO_CART,
  GET_ERRORS,
  ADD_ADDRESS,
  GET_ORDERS,
  GET_WISH_LIST,
  GET_INVOICE,
  GET_SHIPPING_CHARGE_LIST
} from "./type";
import { getProfile } from "./userActions";

export const getProducts = page => async dispatch => {
  await Axios.get(
    BASE_URL + `/inventory/v1/inventory/?page=${page}&size=4`
  ).then(res => {
    if (res.status === 200) {
      dispatch({
        type: GET_PRODUCT,
        payload: {
          list: res.data.content,
          totalPages: res.data.totalPages,
          totalElements: res.data.totalElements,
          pageNumber: res.data.pageable.pageNumber
        }
      });
    }
  });
};

export const addToCart = request => async dispatch => {
  await Axios.post(BASE_URL + "/order/v1/shopping-cart/", request).then(res => {
    if (res.status === 200 && res.data.message === null) {
      dispatch(getCart(request.shoppingCartId));
    } else {
      dispatch({
        type: GET_ERRORS,
        payload: { message: res.data.message }
      });
    }
  });
};

export const getCart = id => async dispatch => {
  await Axios.get(BASE_URL + `/order/v1/shopping-cart/${id}`).then(res => {
    if (res.status === 200) {
      dispatch({
        type: ADD_TO_CART,
        payload: res.data
      });
    }
  });
};

export const removeItemFromCart = (
  shoppingCartId,
  cartDetailId
) => async dispatch => {
  await Axios.delete(
    BASE_URL + `/order/v1/shopping-cart/${shoppingCartId}/${cartDetailId}`
  ).then(res => {
    if (res.status === 200) {
      dispatch(getCart(shoppingCartId));
    }
  });
};

export const getAddress = userAccountId => async dispatch => {
  await Axios.get(BASE_URL + `/order/v1/address/${userAccountId}`).then(res => {
    if (res.status === 200) {
      dispatch({
        type: ADD_ADDRESS,
        payload: res.data
      });
      dispatch({
        type: GET_ERRORS,
        payload: {}
      });
    }
  });
};

export const placeOrder = (request, history) => async dispatch => {
  await Axios.post(BASE_URL + "/order/v1/order/place-order", request).then(
    res => {
      if (res.status === 200) {
        history.push("/home");
      }
    }
  );
};

export const addAddress = request => async dispatch => {
  await Axios.post(BASE_URL + "/order/v1/address/", request).then(res => {
    if (res.status === 200) {
      dispatch(getAddress(request.userAccountId));
      dispatch(getProfile(request.userAccountId));
    }
  });
};

export const getOrders = id => async dispatch => {
  await Axios.get(BASE_URL + `/order/v1/order/${id}`).then(res => {
    if (res.status === 200) {
      dispatch({
        type: GET_ORDERS,
        payload: res.data
      });
    }
  });
};

export const deleteAddress = (addressId, userAccountId) => async dispatch => {
  await Axios.delete(BASE_URL + `/order/v1/address/${addressId}`).then(res => {
    if (res.status === 200) {
      dispatch(getAddress(userAccountId));
      dispatch(getProfile(userAccountId));
    }
  });
};

export const getWishList = id => async dispatch => {
  await Axios.get(BASE_URL + `/order/v1/wish-list/${id}`).then(res => {
    if (res.status === 200) {
      dispatch({
        type: GET_WISH_LIST,
        payload: res.data
      });
    }
  });
};

export const addToWishList = request => async dispatch => {
  await Axios.post(BASE_URL + "/order/v1/wish-list/", request).then(res => {
    if (res.status === 200 && res.data.message === null) {
      dispatch(getWishList(request.userAccountId));
    } else {
      dispatch({
        type: GET_ERRORS,
        payload: { message: res.data.message }
      });
    }
  });
};

export const removeFromWishList = (
  userAccountId,
  inventoryId
) => async dispatch => {
  await Axios.delete(
    BASE_URL + `/order/v1/wish-list/${userAccountId}/${inventoryId}`
  ).then(res => {
    if (res.status === 200) {
      dispatch(getWishList(userAccountId));
    }
  });
};

export const getInvoice = orderId => async dispatch => {
  await Axios.get(BASE_URL + `/order/v1/invoice/${orderId}`).then(res => {
    if (res.status === 200) {
      dispatch({
        type: GET_INVOICE,
        payload: res.data
      });
    }
  });
};

export const updateCart = cartDetail => async dispatch => {
  await Axios.put(BASE_URL + `/order/v1/shopping-cart/`, cartDetail).then(
    res => {
      if (res.status === 200) {
        dispatch(getCart(cartDetail.shoppingCartId));
      }
    }
  );
};

export const getShippingCharge = request => async dispatch => {
  await Axios.post(BASE_URL + `order/v1/shipping-charge/`, request).then(
    res => {
      if (res.status === 200) {
        dispatch({
          type: GET_SHIPPING_CHARGE_LIST,
          payload: res.data
        });
      }
    }
  );
};
