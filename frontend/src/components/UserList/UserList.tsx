import {UserSo} from "../../openapi";
import UserItem from "./UserItem";
import useApiHook from "../../hooks/useApiHook";
import React, {useEffect, useState} from "react";
import {ListGroup} from "react-bootstrap";
import NewUserForm from "../NewUser/NewUserForm";
import Navbar from "../Navbar";


const UserList = () => {
    const api = useApiHook();

    const [users, setUsers] = useState<UserSo[]>([]);

    useEffect(() => {
        api.getUsers().then((usersData) => {
            setUsers(usersData);
        })
    }, [])

    const handleUpdate = () => {
        api.getUsers().then((usersData) => {
            setUsers(usersData);
        })
    }

    return (
        <div className = "text-center align-items-center justify-content-center  d-block">
            <Navbar/>
            <div className="p-4 bg-dark">
                <NewUserForm/>
            </div>
            <ListGroup className="m-3" defaultActiveKey="#link1">
                {users.map((user) => (
                    <UserItem
                        key={user.id}
                        user={user}
                        handleUpdate={handleUpdate}
                    ></UserItem>
                ))}
            </ListGroup>
        </div>
    );
};

export default UserList;