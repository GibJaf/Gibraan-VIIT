import os


class A2_Folders:
    def __init__(self):
        self.CWD = os.getcwd()        
        
    def generate_Folders(self):
        folder_name="/A2_Gibraan"
        try:
            os.mkdir(self.CWD+folder_name)
        except OSError:
            print("Folder creation failed")
        else:
            print("Folder created successfully")



def main():
    work = A2_Folders()
    work.generate_Folders()

    



if __name__ == "__main__":
    main()