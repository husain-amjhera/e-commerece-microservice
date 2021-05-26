import { GET_INVOICE } from "../actions/type";

const initialState = {};

export default function(state = initialState, action) {
  switch (action.type) {
    case GET_INVOICE:
      return action.payload;

    default:
      return state;
  }
}
