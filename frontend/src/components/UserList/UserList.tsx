import {UserSo} from "../../openapi";
import UserItem from "./UserItem";
import useApiHook from "../../hooks/useApiHook";
import {useEffect, useState} from "react";


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
            <table className="table m-5 m-lg-5">
                <thead>
                <tr>
                    <th scope="col">id</th>
                    <th scope="col">Name</th>
                    <th scope="col">Surname</th>
                </tr>
                </thead>
                <tbody>
                {users.map((user) => (
                    <UserItem
                        key={user.id}
                        user={user}
                        handleUpdate={handleUpdate}
                    ></UserItem>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default UserList;