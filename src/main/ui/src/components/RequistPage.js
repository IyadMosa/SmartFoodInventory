import React, { useEffect } from "react";
import { TableScreen } from "@iyadmosa/react-library";
import { useDispatch, useSelector } from "react-redux";
import {
  geToConfirmSharedItemsAll,
  getRequestedSharedItemsAll,
  requestSharedItem,
  shareItem,
} from "../actions/itemAction";
import IconButton from "@material-ui/core/IconButton";
import SaveAltIcon from "@material-ui/icons/SaveAlt";

const RequestPage = () => {
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(getRequestedSharedItemsAll());
  }, []);
  const data = useSelector((state) => state.items.requestedSharedItems);
  const columns = [
    {
      Header: "id",
      accessor: "id",
    },
    {
      Header: "consumed",
      accessor: "consumed",
    },
    {
      Header: "quantity",
      accessor: "quantity",
    },
    {
      Header: "sharingDate",
      accessor: "sharingDate",
    },
    {
      Header: "shared item name",
      accessor: "item.name",
    },
    {
      Header: "shared item id",
      accessor: "item.id",
    },
    {
      Header: "shared item expirationDate",
      accessor: "item.expirationDate",
    },
  ];
  return (
    <TableScreen
      title={"Requested Shared Items"}
      showAdd={false}
      data={data}
      columns={columns}
      onInit={() => dispatch(getRequestedSharedItemsAll())}
    />
  );
};

export default RequestPage;
