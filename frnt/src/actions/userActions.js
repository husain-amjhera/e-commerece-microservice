import Axios from "axios";
import {
  BASE_URL,
  SET_CURRENT_USER,
  GET_ERRORS,
  ADD_CARD,
  GET_PROFILE
} from "./type";

export const loginUser = (loginRequest, history) => async dispatch => {
  try {
    await Axios.post(BASE_URL + "/user/v1/user/login", loginRequest).then(
      res => {
        if (res.status === 200) {
          dispatch({
            type: SET_CURRENT_USER,
            payload: res.data
          });

          dispatch({
            type: GET_ERRORS,
            payload: {}
          });

          history.push("/home");
        }
      }
    );
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data
    });
  }
};

export const registerUser = (registerRequest, history) => async dispatch => {
  try {
    await Axios.post(BASE_URL + "/user/v1/user/", registerRequest).then(res => {
      if (res.status === 200) {
        dispatch({
          type: GET_ERRORS,
          payload: {}
        });
        history.push("/");
      }
    });
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data
    });
  }
};

export const getCards = userAccountId => async dispatch => {
  await Axios.get(BASE_URL + `/user/v1/credit-card/${userAccountId}`).then(
    res => {
      if (res.status === 200) {
        dispatch({
          type: ADD_CARD,
          payload: res.data
        });
        dispatch({
          type: GET_ERRORS,
          payload: {}
        });
      }
    }
  );
};

export const addCard = request => async dispatch => {
  await Axios.post(BASE_URL + "/user/v1/credit-card/", request).then(res => {
    if (res.status === 200) {
      dispatch(getCards(request.userAccountId));
      dispatch(getProfile(request.userAccountId));
    }
  });
};

export const deleteCard = (ccId, userAccountId) => async dispatch => {
  await Axios.delete(BASE_URL + `/user/v1/credit-card/${ccId}`).then(res => {
    if (res.status === 200) {
      dispatch(getCards(userAccountId));
      dispatch(getProfile(userAccountId));
    }
  });
};

export const getProfile = userAccountId => async dispatch => {
  await Axios.get(BASE_URL + `/user/v1/proflie/${userAccountId}`).then(res => {
    if (res.status === 200) {
      dispatch({
        type: GET_PROFILE,
        payload: res.data
      });
      dispatch({
        type: GET_ERRORS,
        payload: {}
      });
    }
  });
};
