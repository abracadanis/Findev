import {UserSo} from "../../openapi";
import UserItem from "./UserItem";

interface UserProps {
    user: UserSo[];
}

const UserList = (props: UserProps) => {
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
                {props.user.map((user) => (
                    <UserItem
                        key={user.id}
                        id={user.id}
                        name={user.name}
                        surname={user.surname}
                    ></UserItem>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default UserList;