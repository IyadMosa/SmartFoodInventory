import {
  ITEMS,
  ITEMS_ERROR,
  REQUESTED_SHARED_ITEMS,
  SHARED_ITEMS,
  TO_CONFIRM_SHARED_ITEMS,
} from "../actions/types";

const initialState = {
  itemsStore: [],
  sharedItems: [],
  requestedSharedItems: [],
  toConfirmSharedItems: [],
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
    case REQUESTED_SHARED_ITEMS:
      return {
        ...state,
        requestedSharedItems: action.payload,
        error: null,
      };
    case TO_CONFIRM_SHARED_ITEMS:
      return {
        ...state,
        toConfirmSharedItems: action.payload,
        error: null,
      };
    default:
      return state;
  }
}
