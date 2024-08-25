import { User } from "./Users";

export interface ForgotPassword {
    id: number;
    otp: number;
    expiryDate: Date;
    user : User;
}