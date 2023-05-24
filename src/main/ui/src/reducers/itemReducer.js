import { ITEMS, ITEMS_ERROR, SHARED_ITEMS } from "../actions/types";

const initialState = {
  itemsStore: [],
  sharedItems: [],
  error: null,
};

export default function itemReducer(state = initialState, action) {
  switch (action.type) {
    case ITEMS:
      return {
        ...state,
        itemsStore: action.payload,
        error: null,
      };
    case ITEMS_ERROR:
      return {
        ...state,
        itemsStore: null,
        error: action.payload,
      };
    case SHARED_ITEMS:
      return {
        ...state,
        sharedItems: action.payload,
        error: null,
      };
    default:
      return state;
  }
}
