__author__ = "Elroi Izaks"
__email__ = "elroiizaks@gmail.com"
__credits__ = "Yehonatan Jacob"

import shutil
import os
from directory_tree import DirectoryTree
import argparse


def copy_directory(src_path: str, dst_path: str, mode: str):
    if os.path.isfile(src_path):
        if (not os.path.exists(dst_path)):
            shutil.copy(src_path, dst_path)
        elif mode == "INFER" and os.path.getmtime(src_path) > os.path.getmtime(dst_path):
            shutil.copy(src_path, dst_path)
    if os.path.isdir(src_path):
        if (not os.path.exists(dst_path)):
            os.mkdir(dst_path)
        for sub in os.listdir(src_path):
            copy_directory(src_path+"\\"+sub, dst_path+"\\"+sub, mode)


if __name__ == '__main__':

    parser = argparse.ArgumentParser()

    parser.add_argument('--source_path', '-s',
                        help="The source path", required=True)
    parser.add_argument('--dest_path', '-d',
                        help="The destination path", required=True)
    parser.add_argument(
        '--mode', '-m', help="The Mode of the pipeline.", choices=["NAIVE", "INFER"], default="NAIVE")

    args = parser.parse_args()

    copy_directory(args.source_path, args.dest_path, args.mode)

    tree = DirectoryTree(args.source_path)
    tree.generate()
