# SecretMessage

This project implements a simple encryption program based on character substitution.
![image](https://github.com/user-attachments/assets/d4014080-8189-4b23-88ea-3fa2b47be0cc)

## Features
- **Generate a new encryption key**
- **View the current key**
- **Encrypt a message**
- **Decrypt a message**
- **Set a custom key**
- **Exit the program**

## How does it work?
The program generates two lists:
1. **Original list**: Contains all printable ASCII characters (from 32 to 126).
2. **Shuffled list**: A copy of the original list, but randomly shuffled.

Each character in the original message is replaced with the corresponding character in the shuffled list, generating an encrypted text. To decrypt, the process is reversed.

## Available Commands
Within the program, you can choose from the following options:
- `(N)` Generate a new encryption key
- `(G)` Get the current key
- `(E)` Encrypt a message
- `(D)` Decrypt a message
- `(S)` Set a custom key
- `(Q)` Exit the program

