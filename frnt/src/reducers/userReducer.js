import { SET_CURRENT_USER } from "../actions/type";

const initialState = {};

export default function(state = initialState, action) {
  switch (action.type) {
    case SET_CURRENT_USER:
      return action.payload;

    default:
      return state;
  }
}
