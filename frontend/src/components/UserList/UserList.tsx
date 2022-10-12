import {UserSo} from "../../openapi";
import UserItem from "./UserItem";
import useApiHook from "../../hooks/useApiHook";
import {useEffect, useState} from "react";
import {Link} from "react-router-dom";
import {ListGroup} from "react-bootstrap";


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
        <div>
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