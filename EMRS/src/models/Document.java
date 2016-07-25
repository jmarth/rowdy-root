package models;

public class Document {

		private long id;
		private long pid;
		private String name;
		private String path;
		private String type;
		
		/**
		 * Document constructor
		 * @param pid Patient ID document belongs to
		 * @param name Name of document
		 * @param path Path to document
		 * @param type Extension of document
		 */
		public Document(long pid, String name, String path, String type) {
			super();
			this.pid = pid;
			this.name = name;
			this.path = path;
			this.type = type;
		}
		
		public Document(long id, long pid, String name, String path, String type) {
			super();
			this.id = id;
			this.pid = pid;
			this.name = name;
			this.path = path;
			this.type = type;
		}

		
		// ----------------------------- GETTERS AND SETTERS ---------------------------------------------
		
		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public long getPid() {
			return pid;
		}

		public void setPid(long pid) {
			this.pid = pid;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}
		
		
		
		
}
