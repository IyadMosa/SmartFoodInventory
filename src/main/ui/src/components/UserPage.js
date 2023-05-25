import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { whoami } from "../actions/authAction";

const UserPage = () => {
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(whoami());
  }, []);
  const user = useSelector((state) => state.auth.user);
  console.log(user);
  return user.username ? (
    <span>
      {user.username}-{user.address.city}, {user.points} Pnts
    </span>
  ) : null;
};

export default UserPage;
