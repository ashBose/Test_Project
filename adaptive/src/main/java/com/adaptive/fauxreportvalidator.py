import csv
import json

'''
experiment_name,sample_id,fauxness,category_guess
exp_1,129,0.56,ambiguous
exp_2,34,0.039,real
control,10,1,fake
'''

class Solution:
	def __init__(self):
		self.category = set(['real', 'fake', 'ambiguous'])
		self.column_header = set(['category_guess','experiment_name','fauxness','sample_id'])
		self.invalid_files = []
		self.column_size  = 4

	def form_parser_message(self, status, error_message):
		return  {
			'status': status,
			'summary': error_message
		}

	def parser(self, reader):
		count = 0
		csv_header = []
		status = True
		error_message = None
		try:
			for v in reader.keys():
				csv_header.append(v.lower())
			if len(csv_header) < self.column_size:
				raise RuntimeError('Rows Truncated')
			if len(csv_header) > self.column_size:
				raise RuntimeError('Extra Header')
			header_set = self.column_header - set(csv_header)
			if len(header_set) != 0:
				raise RuntimeError('Header missing ' + header_set[0])

			for val in csv_header:
				count += 1
				if val == 'fauxness':
					float_value = float(reader[val])
					if 0.0 <= float_value <= 1.0:
						continue
					else:
						raise RuntimeError('Not in Range ' + val + ' value ' + reader[val])
				elif val == 'sample_id':
					int_value = int(reader[val])
					if int_value <= 0:
					    raise RuntimeError('Non Positive value for ' + val + ' value ' + reader[val])
				elif val == 'category_guess':
					if reader[val] not in self.category:
						raise RuntimeError('category is missing ')
				elif val == 'experiment_name':
					if len(reader[val]) == 0:
						raise RuntimeError('Empty value')
		except ValueError:
			status = False
			error_message = 'Non numeric value for column ' + val + ' value ' + reader[val]
		except RuntimeError, e:
			status = False
			error_message = e.message
		except Exception, e:
			status = False
			error_message = e.message
		finally:
			return self.form_parser_message(status, error_message)


	def get_output_format(self):

		return {
			'file_name' : '',
			'summary': { 'status' : '',
			             'reason' : '',
			             'more_info' : None
			             },
			'row_value': {
				'csv_format': None,
				'map_format': None,
				'json_format': None
			}
		}

	def solution(self, input_file, row_num):
		output = self.get_output_format()
		output['file_name'] = input_file
		count = 0
		with open(input_file) as csvfile:
			reader = csv.DictReader(csvfile)
			for row in reader:
				count += 1
				parse_output = self.parser(row)
				if not parse_output['status']:
					self.invalid_files.append(input_file)
					output['summary']['status'] = 'INVALID'
					output['summary']['reason'] =  parse_output['summary']
					return output
				else:
					if row_num == count:
						output['row_value']['csv_format'] = ','.join(row.values())
						output['row_value']['map_format'] = row
						output['row_value']['json_format'] = json.dumps(row)

			output['summary']['status'] = 'VALID'
			output['summary']['reason'] = None
			output['summary']['more_info'] = {'data_count': count}
			return output

S = Solution()
print(S.solution('amyris.csv', 1))
