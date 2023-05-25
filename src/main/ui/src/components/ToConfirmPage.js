import React, { useEffect } from "react";
import { TableScreen } from "@iyadmosa/react-library";
import { useDispatch, useSelector } from "react-redux";
import {
  confirmSharedItem,
  geToConfirmSharedItemsAll,
  requestSharedItem,
  shareItem,
} from "../actions/itemAction";
import IconButton from "@material-ui/core/IconButton";
import CheckIcon from "@mui/icons-material/Check";

const ToConfirmPage = () => {
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(geToConfirmSharedItemsAll());
  }, []);
  const data = useSelector((state) => state.items.toConfirmSharedItems);
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
    {
      Header: "",
      filterable: false,
      sortable: false,
      resizable: false,
      Cell: (porps) => {
        return (
          <div>
            <IconButton
              color="primary"
              onClick={() => {
                dispatch(confirmSharedItem(porps.original.id));
              }}
            >
              <CheckIcon titleAccess={"Confirm item"} />
            </IconButton>
          </div>
        );
      },
      width: 100,
    },
  ];
  return (
    <TableScreen
      title={"To Confirm Shared Items"}
      showAdd={false}
      data={data}
      columns={columns}
      onInit={() => dispatch(geToConfirmSharedItemsAll())}
    />
  );
};

export default ToConfirmPage;
