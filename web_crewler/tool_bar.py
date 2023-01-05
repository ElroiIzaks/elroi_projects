import time
import sys

TOOLBAR_WIDTH = 40


class toolBar:

    def __init__(self, num=100):
        sys.stdout.write("[%s]" % (" " * TOOLBAR_WIDTH))
        sys.stdout.flush()
        sys.stdout.write("\b" * (TOOLBAR_WIDTH+1))
        self._total_num_of_objects = num
        self._current_num_of_objects = 0

    def progerss(self):
        relation_num_and_width = self._total_num_of_objects / TOOLBAR_WIDTH
        for i in range(round((self._current_num_of_objects+1)/relation_num_and_width)- round(self._current_num_of_objects/relation_num_and_width)):
            time.sleep(0.1)
            sys.stdout.write("█")
            sys.stdout.flush()
        self._current_num_of_objects += 1

    def complete_tool_bar(self):
        for i in range(self._current_num_of_objects, self._total_num_of_objects):
            relation_num_and_width = self._total_num_of_objects / TOOLBAR_WIDTH
            for i in range(round((self._current_num_of_objects+1)/relation_num_and_width)- round(self._current_num_of_objects/relation_num_and_width)):
                time.sleep(1/self._current_num_of_objects)
                sys.stdout.write("█")
                sys.stdout.flush()
            self._current_num_of_objects += 1
        sys.stdout.write("]\n")  # this ends the progress bar
