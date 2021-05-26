import { GET_WISH_LIST } from "../actions/type";

const initialState = {
  list: []
};

export default function(state = initialState, action) {
  switch (action.type) {
    case GET_WISH_LIST:
      return {
        ...state,
        list: action.payload
      };

    default:
      return state;
  }
}
